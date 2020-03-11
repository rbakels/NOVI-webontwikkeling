$(document).ready(function() {
    console.log("Ready.");

    $('.novi-filter').click(function() {
        console.log("filter");
        var clicked = $(this);

        $('.novi-filter').removeAttr("selected");
        clicked.attr("selected", clicked.attr("data-search-key"));
        $('#novi-filter-title').text(clicked.attr("data-search-name"));
    });

    $('#novi-search-submit').click(function() {
        var filter = $( $('.novi-filter[selected]')[0] ).attr("data-search-key");
        var search = $('#novi-search').val();

        console.log(filter);
        console.log(search);

        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://pvpcobb152.ont.belastingdienst.nl:9080/onderwijs-instellingen-service/api/search",
            "method": "POST",
            "headers": {
                "Content-Type": "application/json",
                "cache-control": "no-cache"
            },
            "processData": false,
            "data": '{"search": [{"zoekWaarde": "' + search + '","zoekOpdrachtType": "' + filter + '"}]}'
        };

        $.ajax(settings).done(function (response) {
            console.log(response);

            $("#result").empty();

            var table = $('<table></table>').addClass('table');
            var thead = document.createElement('THEAD');

            // Header
            $.each(response, function(index, item) {
                var tr = document.createElement('TR');
                thead.append(tr);

                $.each(item, function(key, value) {
                    var th = document.createElement('TH');
                    th.append(document.createTextNode(key));
                    tr.append(th);
                });

                return false;
            });

            $.each(response, function(index, item) {
                console.log(index);
                console.log(item);

                var tr = document.createElement('TR');
                table.append(tr);

                $.each(item, function(key, value) {
                    console.log(key);
                    console.log(value);

                    var td = document.createElement('TD');
                    td.append(document.createTextNode(value));
                    tr.append(td);
                });
            });

            table.append(thead);
            $("#result").append(table);
        });
    });
});