<#include "/admin/utils/ui.ftl"/>
<@layout>
    <link rel='stylesheet' media='all' href='${base}/dist/css/plugins.css'/>
    <script type="text/javascript" src="${base}/dist/vendors/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>

    <section class="content-header">
        <h1>词条审核</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">首页</a></li>
            <li><a href="${base}/admin/post/list">词条管理</a></li>
            <li class="active">词条审核</li>
        </ol>
    </section>
    <section class="content container-fluid">
        <div class="row">
            <form id="qForm" method="post" action="${base}/admin/post/update">
<#--                <#if view??>-->
                <input type="hidden" name="id" id="word_id" value="${view.id}"/>
                <input type="hidden" name="hid" id="hid" value="${view.hid}"/>
<#--                </#if>-->
                <input type="hidden" name="status" value="${view.status!0}"/>
                <input type="hidden" name="editor" value="${editor!'tinymce'}"/>
                <input type="hidden" id="thumbnail" name="thumbnail" value="${view.thumbnail}">
                <div class="col-md-9 side-left">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">词条审核</h3>
                        </div>
                        <div class="box-body">
                            <div class="form-group">
                                <input type="text" class="form-control" name="title" id="word_title" maxlength="64" placeholder="词条标题" required >
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" id="word_content" rows="10"></textarea>
                            </div>
                            <button type="button"  class="btn btn-warning" style="margin-left: 600px;" id="disagree">审核不通过</button>
                            <button type="button" class="btn btn-primary" style="margin-left: 7px;" id="agree">审核通过</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <script type="text/javascript">
        // $(document).ready(function(){
        //
        // })
        $('#agree').on('click', function(){
            console.log()
            var data = fetch(`http://localhost:9090/admin/post/log/agree`,{
                method:'POST',
                body:JSON.stringify({id:document.getElementById("word_id").value,
                    hid:document.getElementById("hid").value}),
                headers:{'Content-Type':'application/json'}}).then((res)=>{
                return res.text()
            }).then((res)=> {
                window.location.href = "http://localhost:9090/admin/index"
            })
        });
    </script>
</@layout>
