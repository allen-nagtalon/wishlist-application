import React from 'react'
import Wish from './Wish'

const WishList = ({ data, getAllWishes }) => {
  return (
    <main className='main'>
      {data?.content?.length === 0 && <div>Empty List! Please add a wish!</div>}

      <ul className='wish__list'>
        {data?.content?.length > 0 && data.content.map(wish => <Wish wish={wish} key={wish.id} />)}
      </ul>

    </main>
  )
}

export default WishList
