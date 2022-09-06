function init() {
  $(document).ready(function () {
    setup();
  });
}

function setup() {
  var rates = new EventSource('http://localhost:8080/rates');
  rates.addEventListener('rates-changed', function (e) {
    updateRate(JSON.parse(e.data));

  }, false);
}

function updateRate(rate) {
  console.log(rate);
  var container = $('.rates-container');
  var div = $('#rate');
  var divInnerHtml = '<span class="current-price">'
      + rate.currentPrice
      + '<span class="currency-symbol"> uah</span></span>\n'
      + '<span class="current-price">'
      + rate.averagePrice
      + '<span class="currency-symbol"> uah</span></span>\n'
      + '<h1>'
      + rate.name
      + '</h1>'
      + '<h2>'
      + rate.lastUpdated
      + '</h2>';

  if (div.length) {
    div.html(divInnerHtml);
  } else {
    var divHtml = '<div id="rate" class="rate-widget">\n' + divInnerHtml + '\n</div>';
    $('.rates-container').append($(divHtml));
  }
}

init();