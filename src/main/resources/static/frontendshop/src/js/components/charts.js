/**
 * Charts
 * @requires https://github.com/gionkunz/chartist-js
*/

const charts = (() => {

  const chart = document.querySelectorAll('[data-chart]');

  if (chart.length === 0) return;

  // Line chart
  for (let i = 0; i < chart.length; i++) {

    let dataOptions = JSON.parse(chart[i].dataset.chart);
    new Chart(chart[i], dataOptions);
  }
})();

export default charts;
