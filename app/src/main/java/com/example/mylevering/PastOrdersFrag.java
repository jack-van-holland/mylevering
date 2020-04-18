package com.example.mylevering;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PastOrdersFrag extends Fragment {

    private FirebaseDatabase dbase;
    private DatabaseReference dbref;
    private FirebaseAuth auth;
    private FirebaseUser user;

    private String[] months = {"", "January", "February", "March", "April", "May", "June", "July",
    "August", "September", "October", "November", "December"};

    private boolean favOn;
    private SharedPreferences myPrefs;
    private SharedPreferences.Editor editor;
    LinearLayout pastOrderList;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences.Editor editor = myPrefs.edit();

        favOn = myPrefs.getBoolean("fav", false);

        pastOrderList = (LinearLayout) view.findViewById(R.id.pastOrderList);


        View v = new View(getContext());
        final ArrayList<Pair<LinearLayout, Boolean>> favList = new ArrayList<>();
        getPastOrders(favList, v, ((MainActivity)getActivity()));

        populateList(favList);

        final Switch favToggle = view.findViewById(R.id.favoriteToggle);
        if (favOn) {
            favToggle.setChecked(true);
        }
        favToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favOn) {
                    clearList(favList);
                    favToggle.setChecked(false);
                    editor.putBoolean("fav", false);
                    editor.commit();
                    favOn = false;
                    populateList(favList);
                } else {
                    clearList(favList);
                    favToggle.setChecked(true);
                    editor.putBoolean("fav", true);
                    editor.commit();
                    favOn = true;
                    populateList(favList);
                }
            }
        });

    }

    private void clearList(ArrayList<Pair<LinearLayout, Boolean>> favList) {
        if (favOn) {
            for (Pair<LinearLayout, Boolean> pastOrder : favList) {
                if (pastOrder.second) {
                    pastOrderList.removeView(pastOrder.first);
                }
            }
        } else {
            for (Pair<LinearLayout, Boolean> pastOrder : favList) {
                    pastOrderList.removeView(pastOrder.first);
            }
        }
    }

    private void getPastOrders(ArrayList<Pair<LinearLayout, Boolean>> favList, View v, MainActivity a) {
        favList.clear();
        for (Order order : a.pOrders) {
            favList.add(createPastOrderView(favList, v, a, order, getContext()));
        }
    }

    private void populateList(ArrayList<Pair<LinearLayout, Boolean>> favList) {
        for (Pair<LinearLayout, Boolean> pastOrder : favList) {
            if (favOn) {
                if (pastOrder.second) {
                    pastOrderList.addView(pastOrder.first);
                }
            } else {
                pastOrderList.addView(pastOrder.first);
            }
        }
    }

    private Pair<LinearLayout, Boolean> createPastOrderView(final ArrayList<Pair<LinearLayout, Boolean>> favList, View v, final MainActivity a, final Order o, Context c) {
        final LinearLayout past = new LinearLayout(c);
        past.setOrientation(LinearLayout.VERTICAL);
        past.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        past.setId(View.generateViewId());

        final LinearLayout header = new LinearLayout(c);
        header.setId(View.generateViewId());
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        header.setGravity(Gravity.START);

        ImageView check = new ImageView(c);
        check.setId(View.generateViewId());
        check.setImageResource(R.drawable.baseline_check_circle_black_18dp);
        check.setPadding(25, 5 , 0, 5);
        header.addView(check);

        TextView date = new TextView(c);
        date.setId(View.generateViewId());
        date.setText(getDate(o.getCal()));
        date.setTextColor(getResources().getColor(R.color.colorGray));
        date.setPadding(25, 25, 0, 0);
        header.addView(date);

        TextView blank = new TextView(c);
        blank.setId(View.generateViewId());
        blank.setText("");
        blank.setPadding(1000, 25, 0, 0);
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
        blank.setLayoutParams(params);
        header.addView(blank);

        ImageView kitchen = new ImageView(c);
        kitchen.setId(View.generateViewId());
        kitchen.setPadding(0, 0, 0, 0);
        TextView kitchenName = new TextView(c);
        kitchenName.setId(View.generateViewId());
        kitchenName.setPadding(0, 25, 0, 0);
        kitchenName.setTextSize(12f);
        TextView title = new TextView(c);
        title.setId(View.generateViewId());
        title.setPadding(10, 0, 0, 10);
        final TextView description = new TextView(c);
        description.setId(View.generateViewId());
        final TextView descriptionShown = new TextView(c);
        descriptionShown.setId(View.generateViewId());
        descriptionShown.setText("No");
        description.setPadding(75, 0, 0, 0);

        if(o.getType().equals("Butterfly")) {
            kitchen.setImageResource(R.drawable.butterfly);
            kitchenName.setText("Butterfly");
            ButterflyMenuOption butterfly = o.getButterflyMenu();
            title.setText(butterfly.getTitle());
            description.setText(Html.fromHtml(butterfly.getDescription()));
        } else {
            kitchen.setImageResource(R.drawable.fresh);
            kitchenName.setText("Fresh");
            FreshMenuOption fresh = o.getFreshMenu();
            title.setText(fresh.getTitle());
            description.setText(Html.fromHtml(fresh.getDescription()));
        }

        Button reorder = new Button(c);
        reorder.setId(View.generateViewId());
        reorder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        reorder.setText(R.string.reorder);
        reorder.setTextColor(getResources().getColor(R.color.colorAccent));


        final ImageView drop = new ImageView(c);
        drop.setId(View.generateViewId());
        drop.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp2);

        final ImageView heart = new ImageView(c);
        heart.setId(View.generateViewId());

        if (o.isFavorite()) {
            heart.setImageResource(R.drawable.heart_filled);
        } else {
            heart.setImageResource(R.drawable.heart);
        }

        LinearLayout.LayoutParams heartSize = new LinearLayout.LayoutParams(50, 50);
        heart.setLayoutParams(heartSize);
        LinearLayout.LayoutParams kitchenSize = new LinearLayout.LayoutParams(100, 100);
        kitchen.setLayoutParams(kitchenSize);
        LinearLayout.LayoutParams reorderSize = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100);

        reorder.setLayoutParams(reorderSize);
        //LinearLayout content = new LinearLayout(c);
        ConstraintLayout content = new ConstraintLayout(c);
        //content.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        content.setId(View.generateViewId());
        //content.setOrientation(LinearLayout.HORIZONTAL);
        //content.setBackgroundColor(getResources().getColor(R.color.pastDate));
        //content.setGravity(Gravity.START);
        //RelativeLayout.LayoutParams kitchenParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //kitchenParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        content.addView(kitchen);

        content.addView(kitchenName);
        content.addView(heart);
        content.addView(title);
        content.addView(reorder);
        content.addView(drop);
        /*
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, tv1.getId());
*/
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //content.setLayoutParams(new ConstraintLayout.LayoutParams(100, 1000));




        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(content);

        constraintSet.connect(kitchen.getId(), ConstraintSet.TOP, content.getId(), ConstraintSet.TOP, 25);
        constraintSet.connect(kitchenName.getId(), ConstraintSet.TOP, content.getId(), ConstraintSet.TOP, 25);
        constraintSet.connect(reorder.getId(), ConstraintSet.TOP, content.getId(), ConstraintSet.TOP, 25);
        constraintSet.connect(title.getId(), ConstraintSet.TOP, reorder.getId(), ConstraintSet.BOTTOM, 25);
        constraintSet.connect(heart.getId(), ConstraintSet.TOP, reorder.getId(), ConstraintSet.BOTTOM, 25);
        constraintSet.connect(drop.getId(), ConstraintSet.TOP, reorder.getId(), ConstraintSet.BOTTOM, 25);

        constraintSet.connect(kitchen.getId(), ConstraintSet.LEFT, content.getId(), ConstraintSet.LEFT, 25);
        constraintSet.connect(title.getId(), ConstraintSet.LEFT, content.getId(), ConstraintSet.LEFT, 25);
        constraintSet.connect(kitchenName.getId(), ConstraintSet.LEFT, kitchen.getId(), ConstraintSet.RIGHT, 25);
        constraintSet.connect(heart.getId(), ConstraintSet.LEFT, title.getId(), ConstraintSet.RIGHT, 25);
        constraintSet.connect(reorder.getId(), ConstraintSet.RIGHT, content.getId(), ConstraintSet.RIGHT, 25);
        constraintSet.connect(drop.getId(), ConstraintSet.RIGHT, content.getId(), ConstraintSet.RIGHT, 25);


        constraintSet.applyTo(content);

        past.addView(header);
        past.addView(content);

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (descriptionShown.getText().toString().equals("No")) {
                    past.addView(description);
                    drop.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp2);
                    descriptionShown.setText("Yes");
                } else {
                    past.removeView(description);
                    drop.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp2);
                    descriptionShown.setText("No");
                }
            }
        });

        reorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                Bundle bundle = new Bundle();
                if (o.getType().equals("Butterfly")) {
                    intent = new Intent(a, ButterflyMenuItem.class);
                    bundle.putSerializable("selected", o.getButterflyMenu());
                } else {
                    intent = new Intent(a, FreshOrder.class);
                    bundle.putSerializable("selected", o.getFreshMenu());
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbase = FirebaseDatabase.getInstance();
                dbref = dbase.getReference();
                auth = FirebaseAuth.getInstance();
                user = auth.getCurrentUser();

                if (o.isFavorite()) {
                    Order newOrder = o;
                    heart.setImageResource(R.drawable.heart);
                    newOrder.setFavorite(false);

                    dbref.child("users").child(user.getUid()).child("pOrders")
                            .child(newOrder.getId()).setValue(newOrder);
                    clearList(favList);
                    a.updateData();
                    getPastOrders(favList, v, a);
                    populateList(favList);

                } else {
                    Order newOrder = o;
                    newOrder.setFavorite(true);
                    heart.setImageResource(R.drawable.heart_filled);
                    dbref.child("users").child(user.getUid()).child("pOrders")
                            .child(newOrder.getId()).setValue(newOrder);
                    clearList(favList);
                    a.updateData();
                    getPastOrders(favList, v, a);
                    populateList(favList);
                }
            }
        });



        return new Pair<>(past, o.isFavorite());

    }

    private String getDate(OrderDate d) {
        Date today = Calendar.getInstance().getTime();
        if (today.getMonth() + 1 == d.getMonth() && today.getYear() + 1900 == d.getYear()) {
            if (today.getDate() == d.getDay()){
                return "Today at " + d.getHour() + ":" + d.getMinute() + " " + d.getAM();
            } else if (today.getDate() + 1 == d.getDay()) {
                return "Yesterday at" + d.getHour() + ":" + d.getMinute() + " " + d.getAM();
            } else {
                return months[d.getMonth()] + " " + d.getDay() + ", " + d.getYear() + d.getHour() + ":" + d.getMinute() + " " + d.getAM();
            }
        } else {
            return months[d.getMonth()] + " " + d.getDay() + ", " + d.getYear() + d.getHour() + ":" + d.getMinute() + " " + d.getAM();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("Past Orders");
        }

        return inflater.inflate(R.layout.fragment_past_orders, container, false);
    }
}