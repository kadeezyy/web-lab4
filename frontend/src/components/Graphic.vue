<script lang="js">
window.addEventListener('load', () => document.querySelector('svg').addEventListener('click', event => {
    const position = getCursorPosition(event)
    // const r = $("label[for='" + $('[name="value_R"]:checked').attr('id') + "']").html()
    const r = document.getElementById('main_form:spinner_input').getAttribute('aria-valuenow')
    const x = (position.x - 200) / 140 * r
    const y = (200 - position.y) / 140 * r
    // const position = getCursorPosition(event)
    // const x = (position.x - 150) / 100 * r
    // const y = (150 - position.y) / 100 * r
    addResultFromPlot([
        {name: 'x', value: x.toFixed(3)},
        {name: 'y', value: y.toFixed(3)}
    ])
}))

function renderPlot(results, r) {
    document.getElementById('dots').innerHTML = ''
    results.forEach(result => {
        const x = (result.x * 140 / r) + 200
        const y = (result.y * -140 / r) + 200
        const color = result.successful ? 'green' : 'red'
        createPointer(x, y, color)
    })
}

function getCursorPosition(event) {
    const rect = document.querySelector('svg').getBoundingClientRect()
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    };
}

function createPointer(x, y, color) {
    const pointer = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
    pointer.setAttribute('cx', x)
    pointer.setAttribute('cy', y)
    pointer.setAttribute('r', '3')
    pointer.setAttribute('fill', color)
    document.getElementById('dots').appendChild(pointer)
}
</script>

<template>
    <div class="plot">
            <svg height="400" width="400" xmlns="http://www.w3.org/2000/svg">
                <polygon fill="#1e88e5" class="plot_part" points="340,200 200,200 200,130"/>
                <rect fill="#1e88e5" class="plot_part" x="200" y="200" height="70" width="140"/>
                <path fill="#1e88e5" class="plot_part" d="M 60 200 A 140 140, 90, 0, 0, 200 340 L 200 200 Z"/>

                <line stroke="black" x1="0" x2="400" y1="200" y2="200"/>
                <line stroke="black" x1="200" x2="200" y1="0" y2="400"/>
                <polygon fill="black" points="200,0 190,15 210,15"/>
                <polygon fill="black" points="400,200 385,210 385,190"/>

                <line stroke="black" x1="270" x2="270" y1="205" y2="195"/>
                <line stroke="black" x1="340" x2="340" y1="205" y2="195"/>

                <line stroke="black" x1="60" x2="60" y1="205" y2="195"/>
                <line stroke="black" x1="130" x2="130" y1="205" y2="195"/>

                <line stroke="black" x1="195" x2="205" y1="130" y2="130"/>
                <line stroke="black" x1="195" x2="205" y1="60" y2="60"/>

                <line stroke="black" x1="195" x2="205" y1="270" y2="270"/>
                <line stroke="black" x1="195" x2="205" y1="340" y2="340"/>

                <text fill="black" x="270" y="220">½R</text>
                <text fill="black" x="340" y="220">R</text>

                <text fill="black" x="55" y="220">-R</text>
                <text fill="black" x="120" y="220">-½R</text>

                <text fill="black" x="175" y="135">½R</text>
                <text fill="black" x="180" y="65">R</text>

                <text fill="black" x="168" y="273">-½R</text>
                <text fill="black" x="175" y="343">-R</text>

                <text fill="black" x="210" y="10">Y</text>
                <text fill="black" x="390" y="190">X</text>

                <circle fill="black" cx="200" cy="200" r="2"/>
                <svg id="dots" height="400" width="400"/>
            </svg>
        </div>
</template>