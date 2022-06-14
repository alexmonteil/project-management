const fetchData = async (url) => {
    let jsonResponse = await fetch(url);
    let jsResponse = await jsonResponse.json();
    return jsResponse;
};

window.addEventListener("DOMContentLoaded", async event => {

    const timelineDataJS = await fetchData("https://projectio-springboot.herokuapp.com/chartdata/projects-timelines");
    const rows = [];
    timelineDataJS.forEach(obj => {
        rows.push([obj['projectName'], new Date(obj['startDate']), new Date(obj['endDate'])]);
    });

    google.charts.load('current', {'packages':['timeline']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        let container = document.getElementById('timeline');
        let chart = new google.visualization.Timeline(container);
        let dataTable = new google.visualization.DataTable();

        dataTable.addColumn({ type: 'string', id: 'Project' });
        dataTable.addColumn({ type: 'date', id: 'Start' });
        dataTable.addColumn({ type: 'date', id: 'End' });
        dataTable.addRows(rows);
        chart.draw(dataTable);
    }


});