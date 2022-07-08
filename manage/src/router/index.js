import Vue from 'vue'
import VueRouter from 'vue-router'
import Manage from '../views/Manage.vue'
import User from '../views/User.vue'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Person from '../views/Person.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Manage,
    redirect:"/home",
    children:[
      { path: 'user', name: 'User', component: User, meta:{ name:'用户信息' }},
      { path: 'home', name: 'Home', component: Home, meta:{ name:'首页' }},
      { path: 'person', name: 'Person', component: Person, },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router