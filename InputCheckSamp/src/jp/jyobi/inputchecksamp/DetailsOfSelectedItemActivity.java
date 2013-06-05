package jp.jyobi.inputchecksamp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DetailsOfSelectedItemActivity extends Activity {

	// DBに対する各種操作を提供するクラス
	private DBDao dao;

	// DB作成・削除をサポートするクラス
	private DBHelper helper;

	private int rowId;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_of_selected_item);

		TextView noText = (TextView)findViewById(R.id.registNo);
		TextView nameText = (TextView)findViewById(R.id.registName);

		// SQLite を使用する準備
		helper = new DBHelper(this, null, 1);
		SQLiteDatabase db = helper.getReadableDatabase();
		dao = new DBDao(db);

		Intent intent = getIntent();
		rowId = intent.getIntExtra("registNo", 0);

		// DBからIDをもとに検索した結果を取得する
		final DBEntity entity = getDetailData(rowId);

		noText.setText(String.valueOf(rowId));
		nameText.setText(entity.getName());


		// DB削除
		Button deleteBtn = (Button)findViewById(R.id.deleteBtn);
		deleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// 削除したいレコードのIDを渡す
				dao.delete(rowId);

				// DetailActivityを終了してListActivityを再表示する
				// finish()はActivityの終了
				finish();
			}
		});
	}


	private DBEntity getDetailData(int rowId) {

		DBEntity entity = dao.findById(rowId);

		return entity;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_details_of_selected_item, menu);
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
