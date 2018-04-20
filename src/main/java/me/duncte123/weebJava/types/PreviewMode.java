package me.duncte123.weebJava.types;

import me.duncte123.weebJava.helpers.QueryBuilder;
import me.duncte123.weebJava.helpers.QueryParam;

public enum PreviewMode implements QueryParam {

    TRUE {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("preview", "true");
        }
    },

    FALSE {
        @Override
        public void appendTo(QueryBuilder builder) {
            builder.append("preview", "false");
        }
    }

}
