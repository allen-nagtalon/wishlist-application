import { Box, Button, Card, CardContent, CardMedia, Container, IconButton, Typography } from '@mui/material'
import LinkIcon from '@mui/icons-material/Link'
import EditIcon from '@mui/icons-material/Edit'

const wishes = [
  {
    title: 'XG OFFICIAL LIGHT STICK Ver.1（w/XG Trading Card / Set of 7)',
    desc: 'Finally, the official XG light stick is now available! With a design reminiscent of a spaceship and featuring a trigger-type button, it can switch between seven member colours and auto colour change in normal performance mode.',
    url: 'https://shop.xgalx.com/collections/all-item/products/xglx-0100',
    image: 'https://shop.xgalx.com/cdn/shop/files/XGLX-0100_t_01.jpg?v=1714458221'
  },
  {
    title: 'XG OFFICIAL LIGHT STICK Ver.1（w/XG Trading Card / Set of 7)',
    desc: 'Finally, the official XG light stick is now available! With a design reminiscent of a spaceship and featuring a trigger-type button, it can switch between seven member colours and auto colour change in normal performance mode.',
    url: 'https://shop.xgalx.com/collections/all-item/products/xglx-0100',
    image: 'https://shop.xgalx.com/cdn/shop/files/XGLX-0100_t_01.jpg?v=1714458221'
  }
]

function WishListView () {
  return (
    <Container maxWidth='lg'>
      <Box>
        <Typography variant='h2'>
          Jurin's Wishlist
        </Typography>
        <Button>
          Add Wish
        </Button>
        {
          wishes.map((wish) =>
            <Card
              key={wish.title}
              sx={{
                display: 'flex',
                px: 5,
                py: 5,
                my: 5
              }}
            >
              <CardMedia
                component='img'
                sx={{ width: 200 }}
                image={wish.image}
                alt='A wish picture'
              />
              <CardContent>
                <Box>
                  <Typography variant='h5'>
                    {wish.title}
                  </Typography>
                </Box>
                <Box sx={{ display: 'flex', verticalAlign: 'middle' }}>
                  <IconButton>
                    <EditIcon />
                  </IconButton>
                  <IconButton component='a' href={wish.url}>
                    <LinkIcon />
                  </IconButton>
                </Box>
              </CardContent>
            </Card>
          )
        }
      </Box>
    </Container>
  )
}

export default WishListView
