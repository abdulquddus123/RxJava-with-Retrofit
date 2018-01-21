package e.a_qud.rxandroidwithretrofit.Adapter;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import e.a_qud.rxandroidwithretrofit.Model.Info;
import e.a_qud.rxandroidwithretrofit.R;

/**
 * Created by a_qud on 1/21/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Info> mInfoList;
    public DataAdapter(ArrayList<Info> infoList){
        mInfoList=infoList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvName.setText(mInfoList.get(position).getName());
        holder.mTvVersion.setText(mInfoList.get(position).getVer());
        holder.mTvApi.setText(mInfoList.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName,mTvVersion,mTvApi;
        public ViewHolder(View view) {
            super(view);

            mTvName = (TextView)view.findViewById(R.id.tv_name);
            mTvVersion = (TextView)view.findViewById(R.id.tv_version);
            mTvApi = (TextView)view.findViewById(R.id.tv_api_level);
    }
}
}
