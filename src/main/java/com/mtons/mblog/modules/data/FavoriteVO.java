package com.mtons.mblog.modules.data;

import com.mtons.mblog.modules.entity.Favorite;

public class FavoriteVO extends Favorite {
    // extend
    private PostVO post;

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }
}
