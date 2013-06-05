package jp.jyobi.inputchecksamp;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegistAdapter extends BaseAdapter{

	// 最初確認する
	private List<DBEntity> dataList = new ArrayList<DBEntity>();

	private Context context;


	/**
	 * コンストラクタ
	 * @param dataList DBから取得したデータのリスト
	 * @param context
	 */
	RegistAdapter(List<DBEntity> dataList, Context context) {

		super();
		this.dataList = dataList;
		this.context = context;

	}


	@Override
	public int getCount() {

		return dataList.size();

	}

	@Override
	public Object getItem(int position) {

		return dataList.get(position);

	}

	@Override
	public long getItemId(int position) {

		return position;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		DBEntity entity = (DBEntity) getItem(position);

		LinearLayout layout = new LinearLayout(context);
		// LayoutをHORIZONTALに変更
		layout.setOrientation(LinearLayout.HORIZONTAL);
		convertView = layout;

		TextView rowIdText = new TextView(context);
		rowIdText.setText(String.valueOf(entity.getRowId()));
		// テキストサイズを変更
		rowIdText.setTextSize(30);
		layout.addView(rowIdText);

		// 文字の間にスペースを入れる
		TextView spaceText = new TextView(context);
		spaceText.setText(" : ");
		spaceText.setTextSize(30);
		layout.addView(spaceText);

		TextView nameText = new TextView(context);
		nameText.setText(entity.getName());
		// テキストサイズを変更
		nameText.setTextSize(30);
		layout.addView(nameText);

		return convertView;

	}

}
