package top.trumeet.common.event;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import top.trumeet.common.utils.DatabaseUtils;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Trumeet on 2017/12/24.
 * Common 中的不带 greenDao 的模型。Too bad
 */

public class Event {
    // Name in database
    public static final String KEY_ID = DatabaseUtils.KEY_ID;
    public static final String KEY_PKG = "pkg";
    public static final String KEY_TYPE = "type";
    public static final String KEY_DATE = "date";
    public static final String KEY_RESULT = "result";

    @android.support.annotation.IntDef({Type.RECEIVE_PUSH, Type.REGISTER,
    Type.RECEIVE_COMMAND})
    @Retention(SOURCE)
    @Target({ElementType.PARAMETER, ElementType.TYPE,
            ElementType.FIELD, ElementType.METHOD})
    public @interface Type {
        int RECEIVE_PUSH = 0;
        int REGISTER = 2;
        int RECEIVE_COMMAND = 1;
    }

    @android.support.annotation.IntDef({ResultType.OK, ResultType.DENY_DISABLED, ResultType.DENY_USER})
    @Retention(SOURCE)
    @Target({ElementType.PARAMETER, ElementType.TYPE,
            ElementType.FIELD, ElementType.METHOD})
    public @interface ResultType {
        /**
         * Allowed
         */
        int OK = 0;

        /**
         * Deny because push is disabled by user
         */
        int DENY_DISABLED = 1;

        /**
         * User denied
         */
        int DENY_USER = 2;
    }

    /**
     * Id
     */
    private Long id;

    /**
     * Package name
     */
    private String pkg;

    /**
     * Event type
     * @see Type
     */
    @Type
    @NonNull
    private int type;

    /**
     * Event date time (UTC)
     */
    private long date;

    /**
     * Operation result
     */
    @ResultType
    private int result;

    public Event(Long id, @NonNull String pkg, int type, long date, int result) {
        this.id = id;
        this.pkg = pkg;
        this.type = type;
        this.date = date;
        this.result = result;
    }

    public Event() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPkg() {
        return this.pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Create from cursor
     * @param cursor cursor
     * @return Event object
     */
    @NonNull
    public static Event create (@android.support.annotation.NonNull Cursor cursor) {
        return new Event(cursor.getLong(cursor.getColumnIndex(KEY_ID)) /* id */,
                cursor.getString(cursor.getColumnIndex(KEY_PKG)) /* pkg */,
                cursor.getInt(cursor.getColumnIndex(KEY_TYPE)) /* type */,
                cursor.getLong(cursor.getColumnIndex(KEY_DATE)) /* date */,
                cursor.getInt(cursor.getColumnIndex(KEY_RESULT)) /* result */);
    }

    /**
     * Convert to ContentValues
     * @return values object
     */
    @NonNull
    public ContentValues toValues () {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, getId());
        values.put(KEY_PKG, getPkg());
        values.put(KEY_TYPE, getType());
        values.put(KEY_DATE, getDate());
        values.put(KEY_RESULT, getResult());
        return values;
    }
}
