<#import "../common/standardPage.ftl" as p>

<#if article??>
<@p.page title="${article.title}">
        <!-- Post Content Column -->
        <div class="col-lg-8">

          <!-- Title -->
          <h1 class="mt-4">${article.title}</h1>

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
          <p>${article.createdDate?string('dd.MM.yyyy HH:mm:ss')}</p>

          <hr>

          <!-- Post Content -->
          ${article.body}
          <hr>
        </div>
</@p.page>
</#if>
