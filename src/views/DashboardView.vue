<script lang="ts" setup>
import { useAuthStore } from '@/stores/auth';
import { onMounted, ref } from 'vue';
import { usePointStore } from '@/stores/points';
import { reactive } from 'vue';
// import Graphic from "../components/Graphic.vue"

const store = useAuthStore();
const token = store.token;
const data = ref(new Array());

onMounted(async () => {
  let response = await fetch('http://localhost:8080/api/points', {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
    }
  });
  
  data.value = await response.json();
  data.value.forEach(el => {
    usePointStore().data.push(el);
    createDotByData(el)
  })
  
  document.getElementById('main_graph')!.addEventListener('click', async (event) => {
    validateR(coords.r)
    const position = getCursorPosition(event)
    const x = (position.x - 200) / 140 * +coords.r
    const y = (200 - position.y) / 140 * +coords.r
    await usePointStore().requestForm(x.toString(), y.toString(), coords.r.toString())
    sendRequest()
    // renderPlot(data.value)
  })

});

async function sendRequest() {
  const response = await fetch('http://localhost:8080/api/points', {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
    }
  });
  const jsn = await response.json()
  renderPlot(jsn)
  return response;
}

function clickOnSvg() {
  validateR(coords.r)
  const position = getCursorPosition(event)
  const x = (position.x - 200) / 140 * +coords.r
  const y = (200 - position.y) / 140 * +coords.r
  renderPlot(data.value)
  usePointStore().requestForm(x.toString(), y.toString(), coords.r.toString())
}


function renderPlot(results: any ): void {
  document.getElementById('dots')!.innerHTML = ''
  results.forEach((resultPoint: { x: number; y: number; result: boolean; }) => {
      createDotByData(resultPoint)
  })
}
function createDotByData(resultPoint : any) {
  const x = (resultPoint.x * 140 / +coords.r) + 200
  const y = (resultPoint.y * -140 / +coords.r) + 200
  const color = resultPoint.result ? 'green' : 'red'
  createPointer(x, y, color)
}

function getCursorPosition(event : any) {
    const rect = document.querySelector('svg')!.getBoundingClientRect()
    return {
        x: Math.round(event.clientX - rect.left),
        y: Math.round(event.clientY - rect.top)
    };
}

function createPointer(x: number, y: number, color: string) {
    const pointer = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
    pointer.setAttribute('cx', x.toString())
    pointer.setAttribute('cy', y.toString())
    pointer.setAttribute('r', '3')
    pointer.setAttribute('fill', color)
    document.getElementById('dots')!.appendChild(pointer)
}
function validateX(x: number) {
    if (!isNaN(x)) {
        let new_ = (x)
        if (new_ > 3 || new_ < -3) {
          msg('Invalid X', true)
            return false
        }
        msg('', false)
        return true
    } 
    msg('Incorrect x', true)
    return false
}
function validateY(y: number) {
    if (!isNaN(y)) {
        let new_ = (y)
        if (new_ > 5 || new_ < -5) {
          msg('Invalid Y', true)
            return false
        }
        msg('', false)
        return true
    } 
    msg('Incorrect y', true)
    return false
}
function validateR(r: number) {
    if (!isNaN(r)) {
        let new_ = (r)
        if (new_ > 3 || new_ < 1) {
          msg('Invalid R', true)
            return false
        }
        msg('', false)
        return true
    } 

    msg('Incorrect r', true)
    return false
}
function msg(message: string, isActive:boolean) {
  if (isActive) {
    document.getElementById("errors")!.innerText = message
} else {
    document.getElementById("errors")!.innerText = ''
}
  
}
function validateData(x: number, y: number, r: number) {
    return validateX(x) && validateY(y) && validateR(r);
}
const coords = reactive({
    x: 0,
    y: 0,
    r: 2
})
function onSubmit() {
    if (validateData(coords.x, coords.y, coords.r)) {
      usePointStore().requestForm(coords.x.toString(), coords.y.toString(), coords.r.toString())
    }
}
</script>

<template>
  <div class="w-full p-0 m-0">
    <div class="plot w-1/2 float-left m-auto">
      <svg id="main_graph" height="400" width="400" xmlns="http://www.w3.org/2000/svg">
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
          <svg @mousedown="clickOnSvg" id="dots" height="400" width="400"/>
      </svg>
    </div>
    <div class="float-left w-1/2">
      <form id="main_form" class="p-8 bg-white rounded-md shadow-md m-auto max-w-xl" @submit.prevent="onSubmit">
        <div class="mb-4 ">
          <label for="x" class="block text-gray-700 text-sm font-bold mb-2">Значение X</label>
          <input type="text" id="x" placeholder="(-3 .. 3)" v-model="coords.x" 
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <div class="mb-4">
          <label for="y" class="block text-gray-700 text-sm font-bold mb-2">Значение Y</label>
          <input type="text" id="y" placeholder="(-5 .. 5)" v-model="coords.y"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <div class="mb-6">
          <label for="r" class="block text-gray-700 text-sm font-bold mb-2">Значение R</label>
          <input type="text" id="r" placeholder="(1 .. 3)" v-model="coords.r"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <p class="text-red-700 font-bold" id="errors"></p>
        <div class="flex items mb-4">
          <button
            class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit">
            Проверить
          </button>
          <button class="bg-blue-500 hover:bg-blue-700 ml-8 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="button" @click="usePointStore().clearForm">
            Очистить
          </button>
        </div>
    </form>
    </div>
    
  </div>
  
  
  <h1 class="text-3xl mb-8 ">Dashboard</h1>

  

    <table class="w-full text-center border-collapse" style="border: 3px solid black; border-radius: 10%;">
        <thead class="bg-gray-500">
            <tr>
                <th class="p-3">X</th>
                <th class="p-3">Y</th>
                <th class="p-3">R</th>
                <th class="p-3">Результат</th>
                <th class="p-3">Текущее время</th>
            </tr>
        </thead>
        <tbody id="form-body">
            <tr v-for="(row) in usePointStore().data">
                    <td class="p-3">{{row.x}}</td>
                    <td class="p-3">{{row.y}}</td>
                    <td class="p-3">{{row.r}}</td>
                    <td class="p-3">{{row.result }}</td>
                    <td class="p-3">{{row.time }}</td>
            </tr>
        </tbody>
    </table>
</template>

<style>

</style>

