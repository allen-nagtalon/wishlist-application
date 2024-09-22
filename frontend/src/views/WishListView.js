import { Box, Button, Container, IconButton, Typography } from '@mui/material'
import AddIcon from '@mui/icons-material/Add'
import DeleteIcon from '@mui/icons-material/Delete'
import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import WishCardList from '../components/WishCardList/WishCardList'
import ApiInstance from '../services/ApiInstance'
import CustomModal from '../components/CustomModal/CustomModal'

function WishListView (props) {
  const navigate = useNavigate()
  const { wishlistId } = useParams()
  const [wishlist, setWishlist] = useState(null)
  const [wishes, setWishes] = useState(null)

  const handleDelete = () => {
    ApiInstance.delete(`/wishlist/${wishlistId}`)
      .then(() => {
        navigate('/wishlists')
      })
  }

  const [modalOpen, setModalOpen] = useState(false)
  const handleModalToggle = () => setModalOpen(!modalOpen)

  const fetchWishes = () => {
    ApiInstance.get(`/wishes/wishlist/${wishlistId}`)
      .then((res) => {
        setWishes(res.data.data.wishes)
      })
  }

  const fetchWishlist = () => {
    ApiInstance.get(`/wishlist/${wishlistId}`)
      .then((res) => {
        setWishlist(res.data.data.wishlist)
      })
  }

  useEffect(() => {
    fetchWishlist()
    fetchWishes()
  }, [])

  return (
    <Container maxWidth='lg' sx={{ pt: 10, flexGrow: 1 }}>
      <Box display='flex' sx={{ mb: 3 }}>
        <Typography variant='h4' sx={{ mr: 2 }}>
          {wishlist ? wishlist.title : ''}
        </Typography>
        <IconButton onClick={handleDelete}>
          <DeleteIcon />
        </IconButton>
      </Box>
      <Typography sx={{ mb: 3 }}>
        {wishlist ? wishlist.description : ''}
      </Typography>
      <Box sx={{ mb: 3 }}>
        <Button
          component='label'
          variant='contained'
          startIcon={<AddIcon />}
          color='light'
          onClick={handleModalToggle}
          sx={{ borderRadius: 5 }}
        >
          Add New Wish
        </Button>
      </Box>
      <WishCardList wishes={wishes} fetchWishes={fetchWishes} />
      <CustomModal
        wishModal
        modalOpen={modalOpen}
        handleModalToggle={handleModalToggle}
        onSubmit={fetchWishes}
      />
    </Container>
  )
}

export default WishListView
