import { defineStore } from 'pinia';
import router from '@/router';

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => {
    return {
      user: localStorage.getItem('user') != undefined ? JSON.parse(localStorage.getItem('user')!) : null,
      token: localStorage.getItem('token') ? JSON.parse(localStorage.getItem('token')!) : null,
      returnUrl: '/'
    }
  },
  actions: {
    async login(username: string, password: string) {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
      });

      if (response.status === 200) {
        const data = await response.json();
        this.user = data.username;
        this.token = data.token;
        localStorage.setItem('user', JSON.stringify(data.username));
        localStorage.setItem('token', JSON.stringify(data.token));
        
        router.push(this.returnUrl || '/');
      } else {
        throw new Error('Invalid credentials');
      }

    },
    async register(username: string, password: string) {
      const response = await fetch(
          'http://localhost:8080/api/auth/register',
      {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify({
              username, password
          })
      })
      if (response.status === 200) {
          console.log('Success')
      } else {
          throw new Error('Invalid credentials')
      }
  },
    logout() {
      this.user = null;
      this.token = null;
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      localStorage.removeItem('lastResp')
      router.push('/login');
    }
  }
});
