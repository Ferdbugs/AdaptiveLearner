package com.example.adaptivelearner.Provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContractClass {

    private ContractClass() {}

    public static final String AUTHORITY = "com.example.adaptivelearner.provider.UserContentProvider";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(UserTable.TABLE_NAME).build();

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String COLUMN_ID = "_user_id";
        public static final String COLUMN_TOPIC = "_topic";
        public static final String COLUMN_DIFFICULTY = "_difficulty";
        public static final String COLUMN_PERFORMANCE = "_performance";
        public static final String COLUMN_LEARNER_STATE = "_learner_state";
        public static final String COLUMN_DATE = "_date";

    }
}
