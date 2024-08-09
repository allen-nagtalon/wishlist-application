import { useEffect, useState } from 'react'
import './App.css'
import WishList from './components/WishList'
import { getWishes } from './api/WishService'
import { Routes, Route, Navigate } from 'react-router-dom'

function App () {
  const [data, setData] = useState({})
  const [currentPage, setCurrentPage] = useState(0)

  const getAllWishes = async (page = 0, size = 10) => {
    try {
      setCurrentPage(page)
      const { data } = await getWishes(page, size)
      setData(data)
      console.log(data)
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    getAllWishes()
  }, [])

  return (
    <>
      <Routes>
        <Route path='/' element={<Navigate to='/wishes' />} />
        <Route path='/wishes' element={<WishList data={data} getAllWishes={getAllWishes} />} />
      </Routes>
    </>
  )
}

export default App
