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
import File_check from '@/pages/file_check'
import File_passed from '@/pages/file_passed'
import File_be_checked from '@/pages/file_be_checked'
import File_backed from '@/pages/file_backed'
import File_no_uploaded from '@/pages/file_no_uploaded'
import File_detail from '@/pages/file_detail'
import Sum from '@/pages/sum'
import Query from '@/pages/query'
import Centre1 from '@/pages/centre-1'
import Centre2 from '@/pages/centre-2'
import Centre3 from '@/pages/centre-3'
import Centre4 from '@/pages/centre-4'
import Analy1 from '@/pages/analy-1'
import Analy2 from '@/pages/analy-2'
import Analy3 from '@/pages/analy-3'
import Login from '@/pages/login'
import DataExam from '@/pages/data_exam'
import Committed from '@/pages/committed'
import Uncommitted from '@/pages/uncommitted'
import Detail from '@/pages/detail'
import Queryres from '@/pages/query_res'
import Notice_pro from '@/pages/notice'
import AddNotice from '@/pages/addNotice'
import EditNotice from '@/pages/editNotice'
import FileCheck from '@/pages/file_check.vue'
import FileBeChecked from '@/pages/file_be_checked.vue'

import Record from "@/pages-company/record"
import Company from "@/pages-company/layout-company"
import Data_Collection from "@/pages-company/data_collection"
import Data_Collection_Modify from "@/pages-company/data_collection_modify"
import Query_result_com from "@/pages-company/query_result"
import Query_com from "@/pages-company/query"
import Notice from "@/pages-company/notice"
import ReadNotice from "@/pages-company/readNotice"

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
        path: 'data_collection',
        name: 'Data_Collection',
        component: Data_Collection
      }, {
        
        path: 'data_collection_modify',
        name: 'Data_Collection_Modify',
        component: Data_Collection_Modify
      }, {
        path: 'query',
        name: 'Query_com',
        component: Query_com
      }, {
        path: 'query_result',
        name: 'Query_result_com',
        component: Query_result_com
      }, {
        path: 'notice',
        name: 'Notice',
        component: Notice
      }, {
        path: 'readNotice',
        name: 'ReadNotice',
        component: ReadNotice
      }, ]
    },
  
 

    {
      path: '/layout',
      name: 'Layout',
      component: Layout,
      children: [{
        path: 'file_check',
        name: 'File_check',
        component: File_check,
        children:[{
          path: 'file_passed',
        name: 'File_passed',
        component: File_passed,
        },{
          path: 'file_be_checked',
        name: 'File_be_checked',
        component: File_be_checked,
        },{
          path: 'file_backed',
        name: 'File_backed',
        component: File_backed,
        },{
          path: 'file_no_uploaded',
        name: 'File_no_uploaded',
        component: File_no_uploaded,
        }]
      },{
        path: 'file_detail',
        name: 'File_detail',
        component: File_detail
      }, {
        path: 'centre',
        name: 'Centre',
        component: Centre
      },{
        path: 'file_check',
        name: 'FileCheck',
        component: FileCheck,
        children:[{
          path: 'file_be_checked',
          name: 'FileBeChecked', 
          component: FileBeChecked,
        }]
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
      }, {
        path: 'dataexam',
        name: 'DataExam',
        component: DataExam
      },{
        path: 'committed',
        name: 'Committed',
        component: Committed
      },{
        path: 'uncommitted',
        name: 'Uncommitted',
        component: Uncommitted
      },{
        path: 'detail',
        name: 'Detail',
        component: Detail
      },{
        path: 'queryres',
        name: 'Queryres',
        component: Queryres
      },{
        path: 'notice',
        name: 'NOtice_pro',
        component: Notice_pro
      },{
        path: 'addNotice',
        name: 'AddNotice',
        component: AddNotice
      },{
        path: 'editNotice',
        name: 'EditNotice',
        component: EditNotice
      },{
        path: 'centre1',
        name: 'Centre1',
        component: Centre1
      },{
        path: 'centre2',
        name: 'Centre2',
        component: Centre2
      },{
        path: 'centre3',
        name: 'Centre3',
        component: Centre3
      },{
        path: 'centre4',
        name: 'Centre4',
        component: Centre4
      }
       ]
    },
  ]
})
