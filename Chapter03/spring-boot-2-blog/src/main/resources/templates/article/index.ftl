<#import "../common/standardPage.ftl" as p>

<@p.page title="Posts">
        <!-- Post Content Column -->
        <div class="col-lg-8">

          <#if message??>
          <div id="success-alert" class="alert alert-success">
            ${message}
          </div>
          <script type="text/javascript">
            $( document ).ready(function() {
                $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                    $("#success-alert").slideUp(500);
                });
            });
          </script>
          </#if>

          <#if articles?? >
          <#list articles.content as article>
          <!-- Title -->
          <h1 class="mt-4"><a href="/article/show/${article.link}">${article.title}</a></h1>

          <!-- Author -->
          <p class="lead">
            by
            <#if article.author??>
                <a href="#">${article.author.username}</a>
            <#else>
                Annonymous
            </#if>
          </p>

          <hr>

          <!-- Date/Time -->
          <p>Posted on ${article.createdDate?string('dd.MM.yyyy HH:mm:ss')}</p>

          <p>${article.summary}</p>

          <#if article.author?? && user??>
            <#if article.author.username == user.username || user.role?contains("ADMIN")>
              <form id="form_delete_${article.id}" method="post" action="/article/delete/${article.id}"></form>
              <p><a class="btn btn-success" href="/article/edit/${article.id}">Edit</a> <a href="#" class="btn btn-danger" onclick="$('#form_delete_${article.id}').submit();">Delete</a></p>
            </#if>
          </#if>

          <hr>
          </#list>

          <nav aria-label="Page navigation example">
            <ul class="pagination">
              <#if articles.hasPrevious()>
                <li class="page-item"><a class="page-link" href="/article?page=${articles.previousPageable().pageNumber}&size=20">Previous</a></li>
              </#if>
              <#list 1..articles.totalPages as i>
                <li class="page-item"><a class="page-link" href="/article?page=${i-1}&size=20">${i}</a></li>
              </#list>
              <#if articles.hasNext()>
                <li class="page-item"><a class="page-link" href="/article?page=${articles.nextPageable().pageNumber}&size=20">Next</a></li>
              </#if>
            </ul>
          </nav>
          </#if>

        </div>
</@p.page>
