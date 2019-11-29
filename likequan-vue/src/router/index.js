import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import User from '@/components/User'
import Save from '@/components/Save'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/a',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'User',
      component: User
    },
    {
      path: '/save/:id',
      name: 'Save',
      component: Save
    }
  ]
})
