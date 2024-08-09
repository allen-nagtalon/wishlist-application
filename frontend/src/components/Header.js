import React from 'react'

const Header = ({ toggleModal, numOfWishes }) => {
  return (
    <header className='header'>
      <div>
        <h3>Wishes</h3>
        <button onClick={() => toggleModal(true)} className='btn'>
          Add Wish
        </button>
      </div>
    </header>
  )
}

export default Header
