 package com.example.crudsqlitedasar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ListViewHolder> {

    private ArrayList<User> list;
    private DatabaseHandler db;

     public ListUserAdapter(ArrayList<User> list) { this.list = list; }

     @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_user, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
         User user = list.get(position);
         holder.tvName.setText(user.getNamaUser());
         holder.tvDetail.setText(user.getEmail());

         holder.imgOverflow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 db = new DatabaseHandler(view.getContext());

                 PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                 popupMenu.inflate(R.menu.action_menu);
                 popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                     @Override
                     public boolean onMenuItemClick(MenuItem item) {
                         switch (item.getItemId()) {
                             case R.id.delete:
                                 db.delete(user);

                                 Integer idUser = user.getIdUser();
                                 list.remove(position);
                                 notifyItemRemoved(position);
                                 if (idUser == null) {
                                     notifyItemRangeChanged(user.getIdUser(), list.size());
                                 }
                                 break;
                             case R.id.update:
                                 Intent intent1 = new Intent (view.getContext(), FromActivity.class);
                                 intent1.putExtra("aksi", "update");
                                 intent1.putExtra("id_user", user.getIdUser());
                                 intent1.putExtra("nama_user", user.getNamaUser());
                                 intent1.putExtra("email", user.getEmail());
                                 view.getContext().startActivity(intent1);
                                 break;
                         }
                         return true;
                     }
                 });
                 popupMenu.show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail;
        ImageButton imgOverflow;
        RecyclerView lvUser;

        ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNamaUser);
            tvDetail = itemView.findViewById(R.id.tvEmail);
            imgOverflow = itemView.findViewById(R.id.imgOverflow);
        }
    }
}
