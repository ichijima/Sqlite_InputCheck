package jp.jyobi.inputchecksamp;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 * @author 4123134
 *
 */
public class DBDao {

	// テーブルの定数
	private static final String TABLE_NAME = "address";
	private static final String COLUMN_ID = "rowid";
	private static final String COLUMN_NAME = "name";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME};

	// SQLiteDatabase
	private SQLiteDatabase db;

	/**
	 * コンストラクタ
	 * @param db
	 */
	public DBDao(SQLiteDatabase db) {

		this.db = db;

	}

	/**
	 * 全データの取得
	 * @return
	 */
	public List<DBEntity> findAll() {

		List<DBEntity> entityList = new ArrayList<DBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_ID);

		while(cursor.moveToNext()) {

			DBEntity entity = new DBEntity();
			entity.setRowId(cursor.getInt(0));
			entity.setName(cursor.getString(1));
			entityList.add(entity);

		}

		// カーソルをclose
		cursor.close();

		return entityList;

	}


	/**
	 * 特定IDのデータを取得
	 * @param rowId
	 * @return
	 */
	public DBEntity findById(int rowId) {

		String selection = COLUMN_ID + "=" + rowId;
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, selection, null, null, null, null);

		cursor.moveToNext();
		DBEntity entity = new DBEntity();
		entity.setRowId(cursor.getInt(0));
		entity.setName(cursor.getString(1));

		// カーソルをclose
		cursor.close();

		return entity;

	}


	/**
	 * データの登録
	 * @param name
	 * @return
	 */
	public long insert(String name) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		return db.insert(TABLE_NAME, null, values);

	}


	/**
	 * データの更新
	 * @param entity
	 * @return
	 */
	public int update(DBEntity entity) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, entity.getName());

		String whereClause = COLUMN_ID + "=" + entity.getRowId();

		return db.update(TABLE_NAME, values, whereClause, null);

	}


	/**
	 * データの削除
	 * @param rowId
	 * @return
	 */
	public int delete(int rowId) {

		String whereClause = COLUMN_ID + "=" + rowId;
		return db.delete(TABLE_NAME, whereClause, null);

	}

}
