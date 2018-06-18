package me.duncte123.weebJava.types;

import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.helpers.QueryParam;

public enum PreviewMode implements QueryParam {

    PREVIEW {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("preview", "true");
        }
    },

    NO_PREVIEW {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("preview", "false");
        }
    }

}
