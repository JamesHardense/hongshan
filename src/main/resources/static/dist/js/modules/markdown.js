/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2022, hongshan. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/

define('markdown', [
    'codemirror-css',
    'codemirror-theme',
    'codemirror',
    'marked'
    ], function(require, exports, module) {

    require.async(['codemirror-markdown', 'codemirror-keymap', 'app.markdown'], function () {
        MdEditor.initEditor();
    });
});