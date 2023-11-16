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

//省的界面
import Government_Pro from '@/pages/pages-pro/government_home'
import File_check from '@/pages/pages-pro/file_check'
import File_passed from '@/pages/pages-pro/file_passed'
import File_be_checked from '@/pages/pages-pro/file_be_checked'
import File_backed from '@/pages/pages-pro/file_backed'
import File_no_uploaded from '@/pages/pages-pro/file_no_uploaded'
import File_detail from '@/pages/pages-pro/file_detail'
import Sum from '@/pages/pages-pro/sum'
import Query from '@/pages/pages-pro/query'
import Centre1 from '@/pages/pages-pro/centre-1'
import Centre2 from '@/pages/pages-pro/centre-2'
import Centre3 from '@/pages/pages-pro/centre-3'
import Centre4 from '@/pages/pages-pro/centre-4'
import Analy1 from '@/pages/pages-pro/analy-1'
import Analy2 from '@/pages/pages-pro/analy-2'
import Analy3 from '@/pages/pages-pro/analy-3'
import Login from '@/pages/pages-pro/login'
import DataExam from '@/pages/pages-pro/data_exam'
import Committed from '@/pages/pages-pro/committed'
import Uncommitted from '@/pages/pages-pro/uncommitted'
import Detail from '@/pages/pages-pro/detail'
import Queryres from '@/pages/pages-pro/query_res'
import Notice_pro from '@/pages/pages-pro/notice'
import AddNotice from '@/pages/pages-pro/addNotice'
import EditNotice from '@/pages/pages-pro/editNotice'
import FileCheck from '@/pages/pages-pro/file_check.vue'
import FileBeChecked from '@/pages/pages-pro/file_be_checked.vue'
import Queryres2 from '@/pages/pages-pro/query_res2'


//市的界面
import Government_City from '@/pages/pages-city/government_home'
import File_check_City from '@/pages/pages-city/file_check'
import File_passed_City from '@/pages/pages-city/file_passed'
import File_be_checked_City from '@/pages/pages-city/file_be_checked'
import File_backed_City from '@/pages/pages-city/file_backed'
import File_no_uploaded_City from '@/pages/pages-city/file_no_uploaded'
import File_detail_City from '@/pages/pages-city/file_detail'
import Sum_City from '@/pages/pages-city/sum'
import Query_City from '@/pages/pages-city/query'
import Centre1_City from '@/pages/pages-city/centre-1'
import Centre2_City from '@/pages/pages-city/centre-2'
import Centre3_City from '@/pages/pages-city/centre-3'
import Centre4_City from '@/pages/pages-city/centre-4'
import Analy1_City from '@/pages/pages-city/analy-1'
import Analy2_City from '@/pages/pages-city/analy-2'
import Login_City from '@/pages/pages-city/login'
import DataExam_City from '@/pages/pages-city/data_exam'
import Committed_City from '@/pages/pages-city/committed'
import Uncommitted_City from '@/pages/pages-city/uncommitted'
import Detail_City from '@/pages/pages-city/detail'
import Queryres_City from '@/pages/pages-city/query_res'
import Notice_pro_City from '@/pages/pages-city/notice'
import AddNotice_City from '@/pages/pages-city/addNotice'
import EditNotice_City from '@/pages/pages-city/editNotice'
import FileCheck_City from '@/pages/pages-city/file_check.vue'
import FileBeChecked_City from '@/pages/pages-city/file_be_checked.vue'
import Queryres2_City from '@/pages/pages-city/query_res2'
//公司的界面
import Record from "@/pages/pages-company/record"
import Company from "@/pages/pages-company/layout-company"
import Data_Collection from "@/pages/pages-company/data_collection"
import Data_Collection_Modify from "@/pages/pages-company/data_collection_modify"
import Query_result_com from "@/pages/pages-company/query_result"
import Query_com from "@/pages/pages-company/query"
import Notice from "@/pages/pages-company/notice"
import ReadNotice from "@/pages/pages-company/readNotice"







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
      path: '/government-pro',
      name: 'GovermentPro',
      component: Government_Pro,
      children: [{
        path:"",
        redirect:"file_be_checked"
      },{
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
        path: 'centre-1',
        name: 'Centre1',
        component: Centre1
      },{
        path: 'centre-2',
        name: 'Centre2',
        component: Centre2
      },{
        path: 'centre-3',
        name: 'Centre3',
        component: Centre3
      },{
        path: 'centre-4',
        name: 'Centre4',
        component: Centre4
      }
       ]
    }, 

    {
      path: '/government-city',
      name: 'Goverment_City',
      component: Government_City,
      children: [{
        path: 'file_check',
        name: 'File_check_City',
        component: File_check_City,
        children:[{
          path: 'file_passed',
          name: 'File_passed_City',
        component: File_passed_City,
        },{
          path: 'file_be_checked',
        name: 'File_be_checked_City',
        component: File_be_checked_City,
        },{
          path: 'file_backed',
        name: 'File_backed_City',
        component: File_backed_City,
        },{
          path: 'file_no_uploaded',
        name: 'File_no_uploaded_City',
        component: File_no_uploaded_City,
        }]
      },{
        path: 'file_detail',
        name: 'File_detail_City',
        component: File_detail_City
      },  {
        path: 'sum',
        name: 'Sum_City',
        component: Sum_City
      }, {
        path: 'query',
        name: 'Query_City',
        component: Query_City
      }, {
        path: 'analy1',
        name: 'Analy1_City',
        component: Analy1_City
      }, {
        path: 'analy2',
        name: 'Analy2_City',
        component: Analy2_City
      }, {
        path: 'dataexam',
        name: 'DataExam_City',
        component: DataExam_City
      },{
        path: 'committed',
        name: 'Committed_City',
        component: Committed_City
      },{
        path: 'uncommitted',
        name: 'Uncommitted_City',
        component: Uncommitted_City
      },{
        path: 'detail',
        name: 'Detail_City',
        component: Detail_City
      },{
        path: 'queryres',
        name: 'Queryres_City',
        component: Queryres_City
      },{
        path: 'notice',
        name: 'NOtice_pro_City',
        component: Notice_pro_City
      },{
        path: 'addNotice',
        name: 'AddNotice_City',
        component: AddNotice_City
      },{
        path: 'editNotice',
        name: 'EditNotice_City',
        component: EditNotice_City
      },{
        path: 'centre-1',
        name: 'Centre1_City',
        component: Centre1_City
      },{
        path: 'centre-2',
        name: 'Centre2_City',
        component: Centre2_City
      },{
        path: 'centre-3',
        name: 'Centre3_City',
        component: Centre3_City
      },{
        path: 'centre-4',
        name: 'Centre4_City',
        component: Centre4_City
      }
       ]
    }
  ]
})
