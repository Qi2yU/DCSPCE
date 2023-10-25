/*
 * @Author: your name
 * @Date: 2020-04-13 10:40:44
 * @LastEditTime: 2020-04-13 18:20:40
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vueTest\houtai\src\router\index.js
 */
import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/pages/layout'
import Centre from '@/pages/centre'
import Sum from '@/pages/sum'
import Query from '@/pages/query'
import Analy1 from '@/pages/analy-1'
import Analy2 from '@/pages/analy-2'
import Analy3 from '@/pages/analy-3'
import Login from "@/pages/login"

import Record from "@/pages-company/record"
import Company from "@/pages-company/layout-company"
import Upload from "@/pages-company/upload"
import Query_com from "@/pages-company/query"
import Notice from "@/pages-company/notice"
Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/',
      name: 'login',
      component: Login
    },

    {
      path: '/company',
      name: 'Company',
      component: Company,
      children: [{
        path: 'record',
        name: 'Record',
        component: Record
      }, {
        path: 'upload',
        name: 'Upload',
        component: Upload
      }, {
        path: 'query',
        name: 'Query_com',
        component: Query_com
      }, {
        path: 'notice',
        name: 'Notice',
        component: Notice
      }, ]
    },
  
 

    {
      path: '/layout',
      name: 'Layout',
      component: Layout,
      children: [{
        path: 'centre',
        name: 'Centre',
        component: Centre
      }, {
        path: 'sum',
        name: 'Sum',
        component: Sum
      }, {
        path: 'query',
        name: 'Query',
        component: Query
      }, {
        path: 'analy1',
        name: 'Analy1',
        component: Analy1
      }, {
        path: 'analy2',
        name: 'Analy2',
        component: Analy2
      }, {
        path: 'analy3',
        name: 'Analy3',
        component: Analy3
      }
       ]
    },
  ]
})
