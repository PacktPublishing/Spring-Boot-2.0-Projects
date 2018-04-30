<#macro page title>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Blog Management System">
    <meta name="author" content="Shazin Sadakath">

    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link href="/css/blog-post.css" rel="stylesheet">

    <script src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/article">Blog Management System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/article">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/article/new">Write Article
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <#if user??>
                <a class="nav-link" href="#">Welcome, ${user.username}</a>
              <#else>
                <a class="nav-link" href="/login">Login</a>
              </#if>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/logout">Logout</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <#nested>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

          <!-- Search Widget -->
          <div class="card my-4">
            <h5 class="card-header">Search</h5>
            <div class="card-body">
            <form action="/article">
              <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <input type="submit" class="btn btn-secondary" value="Go!"/>
                </span>
              </div>
            </form>
            </div>
          </div>

        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Shazin Sadakath 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="/webjars/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
</#macro>


