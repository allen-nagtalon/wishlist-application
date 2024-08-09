import React from 'react'

const Wish = ({ wish }) => {
  return (
    <>
      <div>
        <img src={wish.photoUrl} alt={wish.title} />
      </div>
      <div>
        <p>{wish.title}</p>
        <p>{wish.description}</p>
        <p>{wish.url}</p>
      </div>
    </>
  )
}

export default Wish
