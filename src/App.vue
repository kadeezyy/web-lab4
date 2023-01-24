<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore();

function logout() {
  auth.logout();
}
</script>

<template>
   <header>
        <div class="bg-gray-500 inline-flex 
        flex-row justify-around w-screen p-4 text-center">
          <span>Тороев Канатбек</span>
          <span>P32131</span>
          <span>Вариант: 13104</span>
        </div>
        <nav class="p-4 w-screen mb-8">
          <RouterLink class="text-2xl" to="/">График</RouterLink>
          <a href="#" class="ml-4 test-indigo-400"
          @click="logout()">Logout</a>
        </nav>
      </header>
      <main class="p-8">
      <RouterView />
    </main>
</template>
