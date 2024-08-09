import { useEffect, useState } from 'react'
import './App.css'
import { getWishes } from './api/WishService'

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
    <div>
      <h1>Hello!</h1>
    </div>
  )
}

export default App
