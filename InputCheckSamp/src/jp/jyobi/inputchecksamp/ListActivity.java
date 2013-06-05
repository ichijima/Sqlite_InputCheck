package jp.jyobi.inputchecksamp;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * DBデータ表示用リスト画面
 * @author 4123134
 *
 */
public class ListActivity extends Activity {

	// DBに対する各種操作を提供するクラス
	private DBDao dao;

	// DB作成・削除をサポートするクラス
	private DBHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);


		// SQLite を使用する準備
		helper = new DBHelper(this, null, 1);
		SQLiteDatabase db = helper.getReadableDatabase();
		dao = new DBDao(db);

		// ListViewを画面上から取得する
		ListView regitstDataListView = (ListView) findViewById(R.id.regitstDataListView);

		regitstDataListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {

				// 選択された項目のViewをLinearLayoutにキャスト
				LinearLayout layout = (LinearLayout)view;

				// LinearLayoutの0番目の子供をTextViewとして取得
				TextView textView = (TextView)layout.getChildAt(0);

				String registNoText = textView.getText().toString();

				// 選択した項目の登録番号
				int registNo = Integer.parseInt(registNoText);


				// 選択された項目の全情報を登録番号を用いてDBから検索＆取得
				DBEntity entity = dao.findById(registNo);

				Intent intent = new Intent(ListActivity.this, DetailsOfSelectedItemActivity.class);
				intent.putExtra("registNo", entity.getRowId());

				startActivity(intent);

			}

		});

	}

	@Override
	protected void onStart() {
		super.onStart();

		// ListViewを画面上から取得する
		ListView listView = (ListView) findViewById(R.id.regitstDataListView);
		listView.setAdapter(createListData());

	}

	public RegistAdapter createListData() {

		//ArrayAdapter<String> adapter =
		//		new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		// ＤＢから全データを取得する
		List<DBEntity> entityList = dao.findAll();

		RegistAdapter adapter = new RegistAdapter(entityList, this);

		return adapter;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list, menu);
		return true;

	}

	/*
	 * アプリ終了時、画面終了時に呼び出され、Helperクラスを閉じる
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	public void onDestroy() {

		super.onDestroy();
		helper.close();

	}

}
