package jp.jyobi.inputchecksamp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	// データベースの名前
	private static final String DB_NAME = "addressdb";


	/**
	 * addressテーブルを作成するSQL
	 * rowid 整数型、主キー
	 * name 文字列型、notNull
	 */
	private static final String CREATE_TABLE_SQL = "" +
			"create table address (" +
			"rowid integer primary key autoincrement, " +
			"name text not null " +
			")";

	/**
	 * addressテーブルを削除するSQL
	 */
	private static final String DROP_TABLE_SQL =
			"drop table if exists address";


	/**
	 * コンストラクタ
	 * @param context
	 * @param factory
	 * @param version
	 */
	public DBHelper(Context context, CursorFactory factory, int version) {

		super(context, DB_NAME, factory, version);

	}


	/*
	 * データベースの作成時に呼ばれる
	 * テーブルを作成するSQLを実行する
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_TABLE_SQL);

	}


	/*
	 * データベースのバージョンアップ時に呼ばれる
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(DROP_TABLE_SQL);
		db.execSQL(CREATE_TABLE_SQL);

	}

}
