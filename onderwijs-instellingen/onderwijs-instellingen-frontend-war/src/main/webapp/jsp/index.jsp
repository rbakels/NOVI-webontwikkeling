<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
          integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/application.css" rel="stylesheet"/>

    <title>Onderwijs Instellingen</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h1>Onderwijs Instellingen</h1>

            <p class="text-warning">
                Zoeken op een onderwijs instelling kan door de zoekbalk hieronder in te vullen.
            </p>

            <div class="input-group mb-3">
                <div class="input-group-prepend" id="button-addon3">
                    <button id="novi-filter-title" class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Instellingsnaam</button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item novi-filter" href="#" data-search-key="INSTELLINGSNAAM" data-search-name="Instellingsnaam" selected="true">Instellingsnaam</a>
                        <a class="dropdown-item novi-filter" href="#" data-search-key="GEMEENTENAAM" data-search-name="Gemeentenaam">Gemeentenaam</a>
                    </div>
                </div>
                <input type="text" class="form-control novi-search" id="novi-search" name="x" placeholder="Zoeken" aria-label="Zoeken" aria-describedby="search">
                <div class="input-group-append" id="search">
                    <button id="novi-search-submit" class="btn btn-outline-secondary novi-search-submit" type="button"><span class="fa fa-search" aria-hidden="true"></span></button>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="result" class="col-md-12">

            </div>
        </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/application.js"></script>
</body>
</html>
