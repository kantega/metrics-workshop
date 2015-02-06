window.addEventListener("load", function() {


    function appendTd(tr, text) {
        var td = tr.ownerDocument.createElement("td");
        td.textContent = text;
        tr.appendChild(td);
        return td;
    }

    function appendTdFloat(tr, text) {
        appendTd(tr, parseFloat(text).toFixed(2)).style.textAlign="right";
    }

    function parseMetrics(data) {
        console.log("Metrics: " + data)

        var timers = document.querySelector("#timers");
        for(var t in data.timers) {
            var tr = document.createElement("tr");
            var timer = data.timers[t];
            appendTd(tr, t);
            appendTd(tr, timer.count);
            appendTdFloat(tr, timer.max);
            appendTdFloat(tr, timer.mean);
            appendTdFloat(tr, timer.min);
            appendTdFloat(tr, timer.p50);
            appendTdFloat(tr, timer.p75);
            appendTdFloat(tr, timer.p95);
            appendTdFloat(tr, timer.p99);
            appendTdFloat(tr, timer.p999);
            appendTdFloat(tr, timer.stddev);
            appendTdFloat(tr, timer.m1_rate);
            appendTdFloat(tr, timer.m5_rate);
            appendTdFloat(tr, timer.m15_rate);
            appendTdFloat(tr, timer.mean_rate);


            tr.setAttribute("class", timers.getElementsByTagName("tr").length % 2 == 0 ? "even-row" : "odd-row");
            timers.appendChild(tr);
        }

        var meters = document.querySelector("#meters");

        for(var m in data.meters) {
            var tr = document.createElement("tr");
            var meter= data.meters[m];
            appendTd(tr, m);
            appendTd(tr, meter.count);
            appendTdFloat(tr, timer.m1_rate);
            appendTdFloat(tr, timer.m5_rate);
            appendTdFloat(tr, timer.m15_rate);
            appendTdFloat(tr, timer.mean_rate);
            tr.setAttribute("class", meters.getElementsByTagName("tr").length % 2 == 0 ? "even-row" : "odd-row");
            meters.appendChild(tr);
        }

        var gauges = document.querySelector("#gauges");

        for(var g in data.gauges) {
            var tr = document.createElement("tr");
            var gauge = data.gauges[g];
            appendTd(tr, g);
            var text;
            console.log("G: " + typeof gauge.value)
            if(gauge.value.toString().indexOf(".") != -1) {
                text = parseFloat(gauge.value.toString()).toFixed(2);
            } else {
                text = gauge.value.toString()
            }
            appendTd(tr, text).style.textAlign="right";


            tr.setAttribute("class", gauges.getElementsByTagName("tr").length % 2 == 0 ? "even-row" : "odd-row");
            gauges.appendChild(tr);
        }

    }


    var xhr = new XMLHttpRequest();

    xhr.open("GET", "../../metrics/pretty");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4) {
            parseMetrics(JSON.parse(xhr.responseText));
        }
    }

    xhr.send();

});