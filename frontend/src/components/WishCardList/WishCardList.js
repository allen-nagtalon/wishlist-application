import { Box, Card, CardActions, CardContent, CardMedia, IconButton, Typography } from '@mui/material'
import Grid from '@mui/material/Unstable_Grid2'
import EditIcon from '@mui/icons-material/Edit'
import LinkIcon from '@mui/icons-material/Link'
import DeleteIcon from '@mui/icons-material/Delete'

function WishCardList (props) {
  return (
    <>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={2}>
          {props.wishes
            ? props.wishes.map((wish, i) => (
              <Grid key={i} xs={4}>
                <Card sx={{ borderRadius: 3 }}>
                  <CardMedia
                    component='img'
                    image={wish.imageUrl}
                    sx={{
                      height: '200px'
                    }}
                  />
                  <CardContent>
                    <Typography sx={{ fontWeight: 600 }}>
                      {wish.title}
                    </Typography>
                    <Typography
                      variant='body2'
                      sx={{
                        overflow: 'hidden',
                        textOverflow: 'ellipsis',
                        display: '-webkit-box',
                        WebkitLineClamp: '2',
                        WebkitBoxOrient: 'vertical'
                      }}
                    >
                      {wish.description}
                    </Typography>
                  </CardContent>
                  <CardActions disableSpacing>
                    <IconButton>
                      <EditIcon />
                    </IconButton>
                    <IconButton>
                      <LinkIcon />
                    </IconButton>
                    <IconButton>
                      <DeleteIcon />
                    </IconButton>
                  </CardActions>
                </Card>
              </Grid>
              ))
            : <></>
          }
        </Grid>
      </Box>
    </>
  )
}

export default WishCardList
