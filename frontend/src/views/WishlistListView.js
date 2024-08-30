import { Box, Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Container, IconButton, Typography } from '@mui/material'
import { useState } from 'react'
import AddIcon from '@mui/icons-material/Add'
import ShareIcon from '@mui/icons-material/Share'
import LockIcon from '@mui/icons-material/Lock'
import LockOpenIcon from '@mui/icons-material/LockOpen'

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
  // const [wishlists, setWishlists] = useState(null)

  return (
    <>
      <Box id='wishlist-listview' bgcolor='misc.bg1'>
        <Container maxWidth='md' sx={{ mt: 10, py: 10 }}>
          <Box sx={{ display: 'flex' }}>
            <Typography variant='h4' sx={{ flexGrow: 1 }}>
              Your Wishlists
            </Typography>
            <Button
              component='label'
              variant='contained'
              startIcon={<AddIcon />}
              color='light'
              sx={{ borderRadius: 5 }}
            >
              Add New Wishlist
            </Button>
          </Box>
          {testWishlists.map((wishlist) => (
            <Card key={wishlist.title} sx={{ borderRadius: 5, my: 3 }}>
              <CardActionArea>
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
                    image={wishlist.photoUrl}
                    sx={{ objectFit: 'cover', width: '250px' }}
                  />
                </Box>
              </CardActionArea>
            </Card>
          ))}
          <Card sx={{ borderRadius: 5 }}>
            <CardActionArea>
              <Box alignItems='center' justifyContent='center' sx={{ display: 'flex', py: 5 }}>
                <AddIcon sx={{ mr: 1 }} />
                <Typography variant='h6'>
                  Create a New Wishlist
                </Typography>
              </Box>
            </CardActionArea>
          </Card>
        </Container>
      </Box>
    </>
  )
}

export default WishlistListView
