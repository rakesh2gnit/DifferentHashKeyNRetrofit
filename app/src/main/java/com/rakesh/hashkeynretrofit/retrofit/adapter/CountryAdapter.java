package com.rakesh.hashkeynretrofit.retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rakesh.hashkeynretrofit.R;
import com.rakesh.hashkeynretrofit.retrofit.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10-02-2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> filteredCountries = null;
    private List<Country> masterCountries = null;
    private TextView txtViewNoResult, txtViewCountryName;
    //CountryCodePicker codePicker;
    private LayoutInflater inflater;
    private EditText editText_search;
    private Context context;

    public CountryAdapter(Context context, List<Country> countries, EditText editText_search, TextView textView_noResult, TextView txtViewCountryName) {
        this.context = context;
        this.masterCountries = countries;
        //this.codePicker = codePicker;
        this.txtViewCountryName = txtViewCountryName;
        this.txtViewNoResult = textView_noResult;
        this.editText_search = editText_search;
        this.inflater = LayoutInflater.from(context);
        setTextWatcher();
        setOnTouchListener();
        this.filteredCountries = getFilteredCountries("");

    }

    private void setOnTouchListener() {
        if (this.editText_search != null) {
            this.editText_search.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_LEFT = 0;
                    final int DRAWABLE_TOP = 1;
                    final int DRAWABLE_RIGHT = 2;
                    final int DRAWABLE_BOTTOM = 3;
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= (editText_search.getRight() - editText_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here
                            editText_search.setText("");
                            applyQuery(editText_search.getText().toString());
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    /**
     * add textChangeListener, to apply new query each time editText get text changed.
     */
    private void setTextWatcher() {
        if (this.editText_search != null) {
            this.editText_search.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    applyQuery(s.toString());
                }
            });

            //if(isKeyboardAutoPopOnSearch()) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
            //}
        }
    }


    /**
     * Filter country list for given keyWord / query.
     * Lists all countries that contains @param query in country's name, name code or phone code.
     *
     * @param query : text to match against country name, name code or phone code
     */
    private void applyQuery(String query) {

        txtViewNoResult.setVisibility(View.GONE);
        query = query.toLowerCase();

        //if query started from "+" ignore it
        if (query.length() > 0 && query.charAt(0) == '+') {
            query = query.substring(1);
        }

        filteredCountries = getFilteredCountries(query);

        if (filteredCountries.size() == 0) {
            txtViewNoResult.setVisibility(View.VISIBLE);
        }
        notifyDataSetChanged();
    }

    private List<Country> getFilteredCountries(String query) {
        List<Country> tempCountryList = new ArrayList<Country>();
        for (Country country : masterCountries) {
            if (country.isEligibleForQuery(query)) {
                tempCountryList.add(country);
            }
        }
        return tempCountryList;
    }


    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = inflater.inflate(R.layout.adapter_country, viewGroup, false);
        CountryViewHolder viewHolder = new CountryViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CountryViewHolder countryCodeViewHolder, final int i) {
        countryCodeViewHolder.setCountry(filteredCountries.get(i));
        countryCodeViewHolder.getMainView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Singleton.setSelectedCountry(filteredCountries.get(i));
                if (view != null && filteredCountries.get(i) != null) {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    txtViewCountryName.setText(filteredCountries.get(i).getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredCountries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout_main;
        TextView textView_name, textView_code;
        View divider;

        public CountryViewHolder(View itemView) {
            super(itemView);
            relativeLayout_main = (RelativeLayout) itemView;
            textView_name = (TextView) relativeLayout_main.findViewById(R.id.textView_countryName);
            textView_code = (TextView) relativeLayout_main.findViewById(R.id.textView_code);
            divider = (View) relativeLayout_main.findViewById(R.id.preferenceDivider);
        }

        public void setCountry(Country country) {
            if (country != null) {
                divider.setVisibility(View.GONE);
                textView_name.setVisibility(View.VISIBLE);
                textView_code.setVisibility(View.VISIBLE);
                textView_name.setText(country.getName() + " (" + country.getCode().toUpperCase() + ")");
                textView_code.setText("" + country.getDialCode());
            } else {
                divider.setVisibility(View.VISIBLE);
                textView_name.setVisibility(View.GONE);
                textView_code.setVisibility(View.GONE);
            }
        }

        public RelativeLayout getMainView() {
            return relativeLayout_main;
        }
    }
}