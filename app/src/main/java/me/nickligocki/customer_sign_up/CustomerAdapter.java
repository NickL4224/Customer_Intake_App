package me.nickligocki.customer_sign_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    Context context;
    List<Customer> customerList;
    RecyclerView rvCustomers;

    final View.OnClickListener onClickListener = new MyOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowId;
        TextView rowName;
        TextView rowCity;
        TextView rowState;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId = itemView.findViewById(R.id.id_text);
            rowName = itemView.findViewById(R.id.cust_name);
            rowCity = itemView.findViewById(R.id.cust_city);
            rowState = itemView.findViewById(R.id.cust_state);
        }
    }

    public CustomerAdapter(Context context, List<Customer> customerList, RecyclerView rvCustomers) {
        this.context = context;
        this.customerList = customerList;
        this.rvCustomers = rvCustomers;
    }


    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_customer, parent, false);
        view.setOnClickListener(onClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int i) {
        Customer customer = customerList.get(i);
        holder.rowId.setText("" + customer.getId());
        holder.rowName.setText(customer.getFirstName() + " " + customer.getLastName());
        holder.rowCity.setText(customer.getCity());
        holder.rowState.setText((customer.getState()));


    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int index = rvCustomers.getChildLayoutPosition(v);
            String customer = customerList.get(index).getFirstName();
            Toast.makeText(context,customer, Toast.LENGTH_LONG).show();
        }
    }
}
