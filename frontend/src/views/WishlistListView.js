import * as React from 'react'
import { Box, Button, Card, CardActionArea, CardContent, CardMedia, Container, Typography } from '@mui/material'
import { useEffect, useState } from 'react'
import AddIcon from '@mui/icons-material/Add'
import ApiInstance from '../services/ApiInstance'
import defaultGiftIcon from '../assets/default-gift.png'
import CustomModal from '../components/CustomModal/CustomModal'

function WishlistListView () {
  const [wishlists, setWishlists] = useState(null)

  const [modalOpen, setModalOpen] = useState(false)
  const handleModalToggle = () => setModalOpen(!modalOpen)

  const fetchUserWishlists = () => {
    ApiInstance.get('/wishlist')
      .then((res) => {
        setWishlists(res.data.data.wishlists)
      })
  }

  useEffect(() => {
    fetchUserWishlists()
  }, [])

  return (
    <>
      <Box id='wishlist-listview' bgcolor='misc.bg1' sx={{ minHeight: '90dvh' }}>
        <Container maxWidth='md' sx={{ pt: 10 }}>
          <Box sx={{ display: 'flex', mb: 3 }}>
            <Typography variant='h4' sx={{ flexGrow: 1 }}>
              Your Wishlists
            </Typography>
            <Button
              component='label'
              variant='contained'
              startIcon={<AddIcon />}
              color='light'
              onClick={handleModalToggle}
              sx={{ borderRadius: 5 }}
            >
              Add New Wishlist
            </Button>
          </Box>
          {wishlists
            ? wishlists.map((wishlist) => (
              <Card key={wishlist.id} sx={{ borderRadius: 5, my: 3 }}>
                <CardActionArea href={`/wishlists/${wishlist.id}`}>
                  <Box sx={{ display: 'flex', height: '250px' }}>
                    <CardContent sx={{ flex: 2, flexGrow: 1 }}>
                      <Box sx={{ ml: 5, mr: 3, my: 3 }}>
                        <Typography variant='h5' sx={{ mb: 2 }}>
                          {wishlist.title}
                        </Typography>
                        <Typography>
                          {wishlist.description}
                        </Typography>
                      </Box>
                    </CardContent>
                    <CardMedia
                      component='img'
                      image={wishlist.photoUrl ? wishlist.photoUrl : defaultGiftIcon}
                      sx={{ objectFit: 'cover', width: '250px' }}
                    />
                  </Box>
                </CardActionArea>
              </Card>
              ))
            : <></>
          }
          <Card sx={{ borderRadius: 5 }}>
            <CardActionArea onClick={handleModalToggle}>
              <Box alignItems='center' justifyContent='center' sx={{ display: 'flex', py: 5 }}>
                <AddIcon sx={{ mr: 1 }} />
                <Typography variant='h6'>
                  Create a New Wishlist
                </Typography>
              </Box>
            </CardActionArea>
          </Card>
          <CustomModal
            wishlistModal
            modalOpen={modalOpen}
            handleModalToggle={handleModalToggle}
            onSubmit={fetchUserWishlists}
          />
        </Container>
      </Box>
    </>
  )
}

export default WishlistListView
