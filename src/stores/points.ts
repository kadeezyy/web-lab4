import router from "@/router";
import { defineStore } from "pinia";
import { useAuthStore } from "./auth";
// import {renderPlot} from "../views/DashboardView"
export const usePointStore = defineStore({
    id: 'point',
    state: () => {
        return {

            data:new Array()
        }
    },
    actions: {
        async requestForm(x: string, y: string, r: string) {
            const response = await fetch(
                'http://localhost:8080/api/points',
            {
                method: 'POST',
                headers: {
                    Authorization: `Bearer ${useAuthStore().token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    x, y, r
                })
            })
            if (response.status === 200) {
                response.json().then(data => {
                    localStorage.setItem('lastResp', JSON.stringify(data))
                    this.data.push(data) 
                    // document.getElementById("dots")!.innerHTML += 
                })
            } else {
                throw new Error('Some error')
            }
        },
        async clearForm() {
            const response = await fetch(
                'http://localhost:8080/api/points',
                {
                    method: 'DELETE',
                    headers: {
                        Authorization: `Bearer ${useAuthStore().token}`,
                        'Content-Type': 'application/json'
                    }
                }
            ).then().catch(() => console.log("an exception occured during clear"))
            window.location.reload()
            // document.getElementById("dots")!.innerHTML =''
            // document.getElementById("form-body")!.innerHTML = ''
            // document.getElementById("errors")!.innerText = ''
        }
    }


})