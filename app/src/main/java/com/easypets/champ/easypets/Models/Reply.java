package com.easypets.champ.easypets.Models;

import android.support.annotation.Nullable;

public class Reply {

    private String replyText;
    private String replyPicPath;

    public Reply(String replyText, @Nullable String replyPicPath) {
        this.replyText = replyText;
        this.replyPicPath = replyPicPath;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public String getReplyPicPath() {
        return replyPicPath;
    }

    public void setReplyPicPath(String replyPicPath) {
        this.replyPicPath = replyPicPath;
    }

}
