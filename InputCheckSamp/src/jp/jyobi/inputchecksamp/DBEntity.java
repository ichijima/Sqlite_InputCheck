package jp.jyobi.inputchecksamp;

/**
 * DBに登録した一件のレコードのデータを保持するクラス
 * @author 4123134
 *
 */
public class DBEntity {

	// テーブルの行番号（レコードのＩＤ）
	private int rowId;
	// 登録名
	private String name;


	/**
	 * rowIdを設定する
	 * @param rowId
	 */
	public void setRowId(int rowId) {

		this.rowId = rowId;

	}


	/**
	 * rowIdを取得する（渡す）
	 * @return
	 */
	public int getRowId() {

		return rowId;

	}


	/**
	 * 登録名を設定する
	 * @param name
	 */
	public void setName(String name) {

		this.name = name;

	}


	/**
	 * 登録名を取得する
	 * @return
	 */
	public String getName() {

		return name;

	}

}
