using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RepetitionAPI.Models;
using RepetitionAPI.Repos;

namespace RepetitionAPI.Controllers
{
    [ApiVersion("1.0")]
    [Route("v{v:apiVersion}/[controller]")]
    [ApiController]
    public class GiftsController : ControllerBase
    {
        private readonly DataContext _context;

        public GiftsController(DataContext context)
        {
            _context = context;
        }

        // GET: api/Gifts
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Gift>>> GetGift()
        {
            return await _context.Gifts.ToListAsync();
        }

        // GET: api/Gifts/5
        [HttpGet("{giftId}")]
        public async Task<ActionResult<Gift>> GetGift(int giftId)
        {
            var gift = await _context.Gifts.FindAsync(giftId);

            if (gift == null)
            {
                return NotFound();
            }

            return gift;
        }

        // PUT: api/Gifts/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutGift(int id, Gift gift)
        {
            if (id != gift.GiftNumber)
            {
                return BadRequest();
            }

            _context.Entry(gift).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!GiftExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Gifts
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Gift>> PostGift(Gift gift)
        {
            _context.Gifts.Add(gift);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetGift", new { id = gift.GiftNumber }, gift);
        }

        // DELETE: api/Gifts/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteGift(int id)
        {
            var gift = await _context.Gifts.FindAsync(id);
            if (gift == null)
            {
                return NotFound();
            }

            _context.Gifts.Remove(gift);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool GiftExists(int id)
        {
            return _context.Gifts.Any(e => e.GiftNumber == id);
        }
    }

    [ApiVersion("2.0")]
    [Route("v{v:apiVersion}/[controller]")]
    [ApiController]
    public class GiftsController2 : ControllerBase
    {
        private readonly GiftRepo _context;

        public GiftsController2(GiftRepo context)
        {
            _context = context;
        }

        // GET: api/Gifts
        [HttpGet]
        public ActionResult<IEnumerable<Gift>> GetGift()
        {
            return _context.GetGifts();
        }

        // GET: api/Gifts/5
        [HttpGet("{giftId}")]
        public ActionResult<Gift> GetGift(int giftId)
        {
            var gift = _context.GetGiftByID(giftId);

            if (gift == null)
            {
                return NotFound();
            }

            return gift;
        }

        // PUT: api/Gifts/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public ActionResult PutGift(int id, Gift gift)
        {
            if (id != gift.GiftNumber)
            {
                return BadRequest();
            }

            //_context.Entry(gift).State = EntityState.Modified;

            // gift.RowVersion = Encoding.ASCII.GetBytes("0x00000000000007D2");
            /*
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!GiftExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }
            */
            _context.UpdateGift(gift);

            return NoContent();
        }

        // POST: api/Gifts
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
       
        [HttpPost]
        public ActionResult<Gift> PostGift(Gift gift)
        {
            _context.InsertGift(gift);           

            return CreatedAtAction("GetGift", new { id = gift.GiftNumber }, gift);
        }

        // DELETE: api/Gifts/5
        [HttpDelete("{id}")]
        public ActionResult DeleteGift(int id)
        {
            var gift = _context.GetGiftByID(id);
            if (gift == null)
            {
                return NotFound();
            }

            _context.DeleteGift(id);

            return NoContent();
        }

        //private bool GiftExists(int id)
        //{
        //    return _context.Gift.Any(e => e.GiftNumber == id);
        //}
       
    }
}
