import * as React from 'react'
import { Box, Button, Card, CardActionArea, CardContent, CardMedia, Container, Paper, styled, TextField, Typography } from '@mui/material'
import Fade from '@mui/material/Fade'
import { useEffect, useState } from 'react'
import { Modal as BaseModal } from '@mui/base/Modal'
import AddIcon from '@mui/icons-material/Add'
import ApiInstance from '../services/ApiInstance'
import defaultGiftIcon from '../assets/default-gift.png'

const testWishlists = [
  {
    title: 'Jurin\'s Wishlist',
    photoUrl: 'https://pbs.twimg.com/media/Fz3FyK5aMAAdBqv?format=jpg&name=large',
    description: 'This is Jurin\'s wishlist. Her birthday is on June 19, 2002. Japanese rapper and model who has risen to fame as the leader of hip hop and R&B girl group XG. She made her debut with the group in March 2022.',
    private: true
  },
  {
    title: 'Maya\'s Wishlist',
    photoUrl: 'https://m.media-amazon.com/images/I/61pMDn6VAtL._AC_UF894,1000_QL80_.jpg',
    description: 'This is Maya\'s wishlist',
    private: false
  }
]

function WishlistListView () {
  const [wishlists, setWishlists] = useState(null)

  const [formState, setFormState] = useState({
    title: '',
    description: ''
  })
  const handleInputChange = ({ target }) => {
    setFormState({ ...formState, [target.name]: target.value })
  }

  const [modalOpen, setModalOpen] = useState(false)
  const handleOpen = () => setModalOpen(true)
  const handleClose = () => setModalOpen(false)

  const handleSubmit = (event) => {
    event.preventDefault()
    console.log(ApiInstance.defaults.headers.Authorization)

    ApiInstance.post('/wishlist',
      {
        title: formState.title,
        description: formState.description
      }
    )
      .then((res) => {
        setModalOpen(false)
        fetchUserWishlists()
        setFormState({
          title: '',
          description: ''
        })
      })
  }

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
              onClick={handleOpen}
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
            <CardActionArea onClick={handleOpen}>
              <Box alignItems='center' justifyContent='center' sx={{ display: 'flex', py: 5 }}>
                <AddIcon sx={{ mr: 1 }} />
                <Typography variant='h6'>
                  Create a New Wishlist
                </Typography>
              </Box>
            </CardActionArea>
          </Card>
          <Modal
            open={modalOpen}
            onClose={handleClose}
            closeAfterTransition
          >
            <Fade in={modalOpen}>
              <Paper sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', px: 15, pt: 10, pb: 15 }}>
                <Typography variant='h5'>
                  Create a Wishlist
                </Typography>
                <Box component='form'>
                  <TextField
                    required
                    fullWidth
                    id='title'
                    label='Title'
                    name='title'
                    value={formState.title}
                    onInput={handleInputChange}
                    autoFocus
                    sx={{ my: 3 }}
                  />
                  <TextField
                    fullWidth
                    id='description'
                    label='Description'
                    name='description'
                    multiline
                    row={2}
                    value={formState.description}
                    onInput={handleInputChange}
                    sx={{ mb: 4 }}
                  />
                </Box>
                <Box>
                  <Button
                    variant='contained'
                    onClick={handleSubmit}
                    sx={{
                      flex: 2,
                      bgcolor: 'accent.dark',
                      borderRadius: 5
                    }}
                  >
                    Save
                  </Button>
                  <Button
                    variant='outlined'
                    onClick={handleClose}
                    sx={{
                      flex: 1,
                      color: 'accent.dark',
                      borderColor: 'accent.dark',
                      borderRadius: 5
                    }}
                  >
                    Cancel
                  </Button>
                </Box>
              </Paper>
            </Fade>
          </Modal>
        </Container>
      </Box>
    </>
  )
}

const Modal = styled(BaseModal)`
  position: fixed;
  z-index: 1300;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
`

export default WishlistListView
