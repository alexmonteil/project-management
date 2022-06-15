const fetchData = async (url) => {
    let jsonResponse = await fetch(url);
    let jsResponse = await jsonResponse.json();
    return jsResponse;
};

window.addEventListener("DOMContentLoaded", async event => {
    let chartDataJS = await fetchData("http://projectio-springboot.herokuapp.com/chartdata/projects-status-counts");

    const labels = [];

    const data = {
         labels: labels,
         datasets: [{
           label: 'Projects statuses',
           backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
           data: [],
         }]
     };

    const config = {
         type: 'doughnut',
         data: data,
         options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: "Projects Statuses"
                }
            }
         }
    };

    chartDataJS.forEach(item => {
        labels.push(item.status);
        data.datasets[0].data.push(item.count);
    });

    new Chart(
        document.getElementById('myChart'),
        config
    );
});

