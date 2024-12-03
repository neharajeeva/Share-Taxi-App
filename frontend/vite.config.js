import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        //target: 'https://tagalong-backend-210589d74651.herokuapp.com', // The Django server
        target: 'http://127.0.0.1:8080', // The Spring Boot server 
        //target: 'https://tag-along-backend.onrender.com', //render Server string
        changeOrigin: true,
      },
    },
  },
})
