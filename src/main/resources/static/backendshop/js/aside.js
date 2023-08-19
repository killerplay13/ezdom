var collapseElements = document.querySelectorAll('[data-toggle="collapse"]');
collapseElements.forEach(function(element) {
    var targetSelector = element.getAttribute('data-target');
    var targetElement = document.querySelector(targetSelector);
    
    element.addEventListener('click', function() {
        if (targetElement.classList.contains('show')) {
            targetElement.classList.remove('show');
        } else {
            targetElement.classList.add('show');
        }
    });
});