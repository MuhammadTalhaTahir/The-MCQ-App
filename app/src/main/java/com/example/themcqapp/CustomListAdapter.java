package com.example.themcqapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Question> data;
    LayoutInflater layoutInflater;
    public CustomListAdapter(Context context, ArrayList<Question> data){
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_custom_list_item_view,null);
        TextView questionView = view.findViewById(R.id.questionView);
        TextView rightAnswerView = view.findViewById(R.id.rightAnswerView);
        TextView selectedAnswerView = view.findViewById(R.id.selectedAnswerView);
        questionView.setText(this.data.get(i).question);
        rightAnswerView.setText(Character.toString(this.data.get(i).rightAnswer));
        selectedAnswerView.setText(
                this.data.get(i).selectedAnswer +
                        (this.data.get(i).rightAnswer == this.data.get(i).selectedAnswer?"  ✅":"  ❌"));
        return view;
    }
}
