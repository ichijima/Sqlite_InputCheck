package jp.jyobi.inputchecksamp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * DB登録画面用
 * @author 4123134
 *
 */
public class MainActivity extends Activity {

	// DBに対する各種操作を提供するクラス
	private DBDao dao;

	// DB作成・削除をサポートするクラス
	private DBHelper helper;

	// 名前入力用EditText
	private EditText nameEdit;

	private String checkText;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// SQLite を使用する準備、オブジェクトを生成する、newで使用しますよという意思表示
		helper = new DBHelper(this, null, 1);
		SQLiteDatabase db = helper.getReadableDatabase();
		dao = new DBDao(db);

		// 追加ボタンを画面から取得
		Button registBtn = (Button)findViewById(R.id.registButton);
		nameEdit = (EditText)findViewById(R.id.nameEdit);

		// onClickListenerを設定
		registBtn.setOnClickListener(new OnClickListener() {

			// TODO
			@Override
			public void onClick(View arg0) {

				checkText = nameEdit.getText().toString();
				if(isNum(checkText) == false){

					// falseの場合、入力する名前として正しい
					// DBにEditTextのデータを登録する
					registData();

				}else {

					// trueの場合、入力する名前として間違っている
					Toast.makeText(MainActivity.this, String.valueOf("入力が間違っています"), Toast.LENGTH_LONG).show();

				}

			}

		});



		// DB登録データの表示用リスト画面への画面遷移ボタンを取得
		Button showListBtn = (Button)findViewById(R.id.showListButton);
		showListBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// DBデータ表示用リスト画面への画面遷移
				Intent intent = new Intent(MainActivity.this, ListActivity.class);
				startActivity(intent);

			}

		});

	}


	/**
	 * 文字列を検査する
	 * @param checkText
	 * @return
	 */
	public boolean isNum(String checkText) {

		// 文字列の長さまで繰り返す
		for(int i = 0; i < checkText.length(); i++) {

			// charAt この文字列の何番目を取り出すことが出来る
			char c = checkText.charAt(i);
			char c1 = '0';
			char c2 = '9';

			// 0から9の間の文字が含まれればtrue
			// 文字コード同士の比較
			if (c1 <= c && c <= c2) { // TODO

				// 整数が含まれている、名前として間違っている
				return true;

			}

		}

		// 整数が含まれていない、名前として正しい
		return false;

	}



	/**
	 * データベースへデータの登録
	 * @param view
	 */
	public void registData() {

		// Daoのinsertメソッドを用いてEditTextのデータをDBに挿入
		dao.insert(nameEdit.getText().toString());

		// EditTextを初期化
		nameEdit.setText(null);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
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
